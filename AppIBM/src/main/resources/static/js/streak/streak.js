// Select the DOM elements
const daysTag = document.querySelector(".days"); // Container for calendar days
const streakElement = document.querySelector(".user-streak"); // Container for streak information

// Get current date and month
let date = new Date();
const currYear = date.getFullYear();
const currMonth = date.getMonth();

// Array of month names
const months = ["January", "February", "March", "April", "May", "June", "July",
  "August", "September", "October", "November", "December"];

// Function to fetch streak count and streak start date from the server
const fetchStreakInfo = async () => {
  try {
    // Simulate fetching streak information
    const streakCount = await getStreakCount(); // Get streak count dynamically
    const streakStartDate = new Date(); // Initialize streak start date

    // Set streak start date to 'streakCount' days ago from the current date
    streakStartDate.setDate(streakStartDate.getDate() - streakCount);

    return {streakCount: streakCount, streakStartDate};
  } catch (error) {
    console.error('Error fetching streak information:', error);
    return {streakCount: 0, streakStartDate: null}; // Default values if there's an error
  }
};

// Function to get streak count dynamically
const getStreakCount = async () => {
  try {
    // Simulate fetching streak count from the server
    const response = await fetch('yourApiEndpoint');
    const data = await response.json();
    return data.streakCount || 0; // Return streak count or 0 if undefined
  } catch (error) {
    console.error('Error fetching streak count:', error);
    return 0; // Default to 0 if there's an error
  }
};

// Function to render the calendar view
const renderCalendar = async () => {
  const {streakCount, streakStartDate} = await fetchStreakInfo(); // Fetch streak information
  const firstDayofMonth = new Date(currYear, currMonth, 1).getDay(); // Get the day of the week for the first day of the month
  const lastDateofMonth = new Date(currYear, currMonth + 1, 0).getDate(); // Get the last date of the month
  let liTag = ""; // Variable to store HTML list items

  // Loop through each day of the current month
  for (let i = 1; i <= lastDateofMonth; i++) {
    // Determine if the current day is today
    let isToday = i === date.getDate() && currMonth === date.getMonth()
    && currYear === date.getFullYear() ? "active" : "";

    // Determine if the current day is part of the streak
    let isStreakDay = streakStartDate && isPastStreakDay(streakStartDate,
        new Date(currYear, currMonth, i)) ? "streak" : "";

    // Combine CSS classes for the current day
    let classNames = `${isToday} ${isStreakDay}`.trim();

    // Generate HTML list item for the current day
    liTag += `<li class="${classNames}">${i}</li>`;
  }

  // Add empty slots for days before the first day of the month
  for (let i = 0; i < firstDayofMonth; i++) {
    liTag = `<li class="inactive"></li>` + liTag;
  }

  // Append generated HTML to the container for calendar days
  daysTag.innerHTML = liTag;

  // Set streak information in the container for streak information
  streakElement.innerText = `${streakCount} day streak`;
}

// Function to check if a given date is part of the streak period
const isPastStreakDay = (streakStartDate, checkDate) => {
  return checkDate >= streakStartDate && checkDate <= date;
}

// Call the renderCalendar function to initialize the calendar view
renderCalendar();
