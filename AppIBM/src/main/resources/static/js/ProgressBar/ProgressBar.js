const containers = document.querySelectorAll('.status-courses');
const closeButtons = document.querySelectorAll('.close-button');

containers.forEach((container, index) => {
  // Store the original width value of container
  container.dataset.originalWidth = getComputedStyle(container).width;

  container.addEventListener('click', function () {
    this.classList.add('expanded');
    this.style.width = '100%'; // Scale the width of the clicked container
    this.style.order = '-1'; // Move the clicked container to the first position

    // Show the close button
    const closeButton = this.querySelector('.close-button.hidden');
    if (closeButton) {
      closeButton.classList.remove('hidden');
    }

    // Show the course descriptions and buttons
    const descriptions = this.querySelectorAll('p.hidden');
    const buttons = this.querySelectorAll('.course-button.hidden');
    descriptions.forEach(description => description.classList.remove('hidden'));
    buttons.forEach(button => button.classList.remove('hidden'));

    // Enable pointer events for the course links and hover elements
    const links = this.querySelectorAll('a');
    const hovers = this.querySelectorAll('.lighthover');
    links.forEach(link => link.style.pointerEvents = 'auto');
    hovers.forEach(hover => hover.style.pointerEvents = 'auto');

    containers.forEach(otherContainer => {
      if (otherContainer !== this) {
        otherContainer.style.opacity = '0'; // Immediately hide the other containers
      }
    });
  });

  closeButtons[index].addEventListener('click', function (event) {
    event.stopPropagation();
    container.classList.remove('expanded');
    container.style.width = container.dataset.originalWidth // Reset the width of the clicked container

    // Hide the close button
    if (this.classList.contains('hidden') === false) {
      this.classList.add('hidden');
    }

    // Hide the course descriptions and buttons
    const descriptions = container.querySelectorAll(
        '.course-description:not(.hidden)');
    const buttons = container.querySelectorAll('.course-button:not(.hidden)');
    descriptions.forEach(description => description.classList.add('hidden'));
    buttons.forEach(button => button.classList.add('hidden'));

    containers.forEach(otherContainer => {
      if (otherContainer !== this) {
        otherContainer.style.opacity = '1'; // Show the other containers
      }
    });
    container.style.order = '0'; // Reset the order of the clicked container
  });
});
