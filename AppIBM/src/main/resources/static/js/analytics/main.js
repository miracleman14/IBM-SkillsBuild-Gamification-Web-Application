/**
 * @module main
 * This module fetches data when the page loads.
 */

import fetchData from './fetchData.js';

/**
 * Fetches data when the page loads and logs an error if the fetch fails.
 * @function
 */
fetchData().catch(error => console.error('Error fetching data:', error));
