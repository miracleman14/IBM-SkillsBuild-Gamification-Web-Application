/**
 * @module displayCourseInfo
 * This module provides a function to display course information.
 */

/**
 * Displays course information.
 * @function displayCourseInfo
 * @param {Object} course - The course object.
 */
function displayCourseInfo(course) {
  // Get the element where the course information will be displayed
  const infoContainer = document.getElementById('infoContainer');

  // Create the HTML for the course information
  const html = `
    <h3>${course.name}</h3>
    <p>Started: ${course.started}</p>
    <p>Completed: ${course.completed}</p>
    <p>Rated: ${course.rated}</p>
    <p>Average Rating: ${course.averageRating}</p>
    <p>Enrollment Last 30 Days: ${course.enrollmentLast30Days}</p>
  `;

  // Set the HTML of the infoContainer to the created HTML
  infoContainer.innerHTML = html;
}

export default displayCourseInfo;
