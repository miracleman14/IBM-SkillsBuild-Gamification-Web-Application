/**
 * @module fetchData
 * This module fetches data from the server.
 */

import createChart from "./chart.js";

/**
 * Fetches data from the server and optionally creates a new chart with the fetched data.
 * @function fetchData
 * @param {boolean} createNewChart - Whether to create a new chart after fetching the data. Default is true.
 * @returns {Promise<Array>} A promise that resolves to an array of data.
 */
async function fetchData(createNewChart = true) {
  const response = await fetch('/analyticsData');
  const data = await response.json();
  if (createNewChart) {
    createChart(data, '');
  }
  return data;
}

export default fetchData;