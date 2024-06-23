/**
 * @module chart
 * This module creates a chart with the fetched data.
 */

import displayCourseInfo from "./displayCourseInfo.js";

/**
 * Create a new Chart.js plugin
 * @type {{afterDraw: clickLabelPlugin.afterDraw, onClick: clickLabelPlugin.onClick, id: string}}
 */
const clickLabelPlugin = {
  id: 'clickLabelPlugin',
  afterDraw: function (chart) {
    const xAxis = chart.scales['x'];
    chart.labelBoundaries = xAxis.ticks.map((value, index) => {
      let labelWidth;
      if (index === xAxis.ticks.length - 1) {
        labelWidth = xAxis.getPixelForTick(index) - xAxis.getPixelForTick(
            index - 1);
      } else {
        labelWidth = xAxis.getPixelForTick(index + 1) - xAxis.getPixelForTick(
            index);
      }
      const left = xAxis.getPixelForTick(index) - labelWidth / 2;
      const right = xAxis.getPixelForTick(index) + labelWidth / 2;
      const bottom = xAxis.bottom;
      const top = xAxis.bottom - xAxis.height;

      const boundary = {left, right, top, bottom, label: value.label};

      return boundary;
    });
  },
  onClick: function (event, chart, courseNames, data) {
    // Call the 'afterDraw' hook to ensure 'chart.labelBoundaries' is set
    this.afterDraw(chart);

    const eventPosition = {
      x: event.clientX - chart.canvas.getBoundingClientRect().left,
      y: event.clientY - chart.canvas.getBoundingClientRect().top
    };

    const clickedLabel = chart.labelBoundaries.find(
        ({left, right, top, bottom}) =>
            eventPosition.x >= left && eventPosition.x <= right &&
            eventPosition.y >= top && eventPosition.y <= bottom
    );

    if (clickedLabel) {
      const courseName = courseNames.find(name => {
        // Compare the course names with the labels without considering the "..." at the end
        const truncatedName = name.length > 30 ? name.substring(0, 30) + '...'
            : name;
        return truncatedName === clickedLabel.label;
      });

      if (courseName) {
        const course = data.find(item => item.name === courseName);
        displayCourseInfo(course);
        createChart([course], '');
      }
    }
  }
};

/**
 * Creates a chart with the fetched data.
 * @function createChart
 * @param {Array} data - The data to display in the chart.
 * @param {string} filter - The filter to apply to the data.
 */
function createChart(data, filter) {
  // Prepare the data
  const courses = data.map(item => {
    // Check if there is only one course being displayed
    if (data.length === 1) {
      // If there is only one course, don't truncate the course name
      return item.name;
    } else {
      // If there are multiple courses, truncate the course name to 30 characters and append "..." if it's longer
      return item.name.length > 30 ? item.name.substring(0, 30) + '...'
          : item.name;
    }
  });
  const startedData = data.map(item => item.started);
  const completedData = data.map(item => item.completed);
  const ratedData = data.map(item => item.rated);
  const courseNames = data.map(item => item.name);

  // Create a new canvas element
  const canvas = document.createElement('canvas');
  canvas.id = 'courseAnalyticsChart';

  // Append the canvas to the parent container
  const chartContainer = document.getElementById('chartContainer');
  chartContainer.innerHTML = '';
  chartContainer.appendChild(canvas);

  const ctx = canvas.getContext('2d');

  // Determine the type and datasets of the chart based on the selected filter
  let type, datasets;
  switch (filter) {
    case 'enrollment':
      type = 'bar';
      datasets = [
        {
          label: 'Started',
          data: startedData,
          backgroundColor: 'red',
          stack: 'Stack 0'
        },
        {
          label: 'Completed',
          data: completedData,
          backgroundColor: 'blue',
          stack: 'Stack 0'
        }
      ];
      break;
    case 'trending':
      type = 'line';
      datasets = [
        {
          label: 'Enrollment Last 30 Days',
          data: data.map(item => item.enrollmentLast30Days),
          fill: false,
          borderColor: 'blue'
        }
      ];
      break;
    case 'highestCompletionRate':
    case 'lowestCompletionRate':
      type = 'bar';
      datasets = [
        {
          label: 'Completion Rate',
          data: data.map(
              item => (item.completed / (item.started + item.completed)) * 100),
          backgroundColor: 'green'
        }
      ];
      break;
    case 'averageRating':
      type = 'bar';
      datasets = [
        {
          label: 'Average Rating',
          data: data.map(item => item.averageRating),
          backgroundColor: 'purple'
        }
      ];
      break;
    default:
      type = 'bar';
      datasets = [
        {
          label: 'Started',
          data: startedData,
          backgroundColor: 'red'
        },
        {
          label: 'Completed',
          data: completedData,
          backgroundColor: 'blue'
        },
        {
          label: 'Rated',
          data: ratedData,
          backgroundColor: 'green'
        }
      ];
  }

  // Create the chart with the determined type and datasets
  let chart = new Chart(ctx, {
    type: type,
    data: {
      labels: courses,
      datasets: datasets
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      scales: {
        y: {
          beginAtZero: true
        }
      },
      title: {
        display: true,
        text: 'Course Analytics'
      },
      plugins: [clickLabelPlugin]
    }
  });

  // Add a 'click' event listener to the canvas
  canvas.addEventListener('click', function (event) {
    // Call the 'onClick' hook of the 'clickLabelPlugin'
    clickLabelPlugin.onClick(event, chart, courseNames, data);

    const eventPosition = {
      x: event.clientX - chart.canvas.getBoundingClientRect().left,
      y: event.clientY - chart.canvas.getBoundingClientRect().top
    };
  });

  // Add a 'mousemove' event listener to the canvas
  canvas.addEventListener('mousemove', function (event) {
    // Call the 'afterDraw' hook to ensure 'chart.labelBoundaries' is set
    clickLabelPlugin.afterDraw(chart);

    const eventPosition = {
      x: event.clientX - chart.canvas.getBoundingClientRect().left,
      y: event.clientY - chart.canvas.getBoundingClientRect().top
    };

    const hoveredLabel = chart.labelBoundaries.find(
        ({left, right, top, bottom}) =>
            eventPosition.x >= left && eventPosition.x <= right &&
            eventPosition.y >= top && eventPosition.y <= bottom
    );

    // If the mouse is over a label, change the cursor to a pointer
    // Otherwise, change it back to the default cursor
    chart.canvas.style.cursor = hoveredLabel ? 'pointer' : 'default';
  });
}

export default createChart;
