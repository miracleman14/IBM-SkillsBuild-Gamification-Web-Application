/**
 * @module eventListeners
 * This module adds event listeners to the DOM elements.
 */

import fetchData from './fetchData.js';
import createChart from './chart.js';
import sortData from './sortData.js';
import {displayMatchingCourses, searchCourse} from './search.js';

/**
 * Adds event listeners to the DOM elements.
 * @function
 */
document.addEventListener('DOMContentLoaded', (event) => {
  let shouldDisplayDropdown = true;

  /**
   * Event listener for the search button.
   */
  document.getElementById('searchButton').addEventListener('click',
      searchCourse);

  /**
   * Event listener for the clear button.
   */
  document.getElementById('clearButton').addEventListener('click', function () {
    // Clear the search bar and the dropdown
    document.getElementById('search').value = '';
    document.getElementById('searchDropdown').innerHTML = '';
  });

  /**
   * Event listener for the search bar to update the dropdown.
   */
  document.getElementById('search').addEventListener('input',
      async function () {
        if (shouldDisplayDropdown) {
          const data = await fetchData(false);
          displayMatchingCourses(this.value.toLowerCase(), data);
        }
        // Check if the input's value matches one of the options in the datalist
        const optionFound = Array.from(
            document.getElementById('searchDropdown').options).some(
            option => option.value === this.value);
        if (optionFound) {
          // If it does, call the searchCourse function
          await searchCourse({target: {value: this.value}});
          shouldDisplayDropdown = false;
          // Clear the dropdown
          document.getElementById('searchDropdown').innerHTML = '';
        } else {
          shouldDisplayDropdown = true;
        }
      });

  /**
   * Event listener for the search bar to refresh the graphs.
   */
  document.getElementById('search').addEventListener('keydown',
      async function (event) {
        // Only call searchCourse when the Enter key is pressed
        if (event.key === 'Enter') {
          event.preventDefault(); // prevent form submission
          await searchCourse();
        }
      });

  /**
   * Event listener for the filter selection.
   */
  document.getElementById('filter').addEventListener('change',
      async function () {
        // Clear the infoContainer element
        document.getElementById('infoContainer').innerHTML = '';

        const data = await fetchData(true);

        // Check if the selected filter is 'All'
        if (this.value === 'all') {
          // Create a new chart with the original, unsorted data
          createChart(data, '');
        } else {
          // Sort the data based on the selected filter
          const sortedData = sortData(data, this.value);
          // Create a new chart with the sorted data and the selected filter
          createChart(sortedData, this.value);
        }
      });
});
