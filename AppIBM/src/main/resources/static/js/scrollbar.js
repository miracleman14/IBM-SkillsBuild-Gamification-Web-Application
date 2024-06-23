/* To use this file, add the following line at the end of <body> of your HTML file:
<script src="${pageContext.request.contextPath}/js/scroll.js"></script>
 */

// Function to check if scrollbar is visible
function isScrollbarVisible(element) {
  return element.scrollHeight > element.clientHeight;
}

// Function to get scrollbar width
function getScrollbarWidth() {
  // Creating invisible container
  const outer = document.createElement('div');
  outer.style.visibility = 'hidden';
  outer.style.overflow = 'scroll'; // forcing scrollbar to appear
  document.body.appendChild(outer);

  // Creating inner element and placing it in the container
  const inner = document.createElement('div');
  outer.appendChild(inner);

  // Calculating difference between container's full width and the child width
  const scrollbarWidth = (outer.offsetWidth - inner.offsetWidth);

  // Removing temporary elements from the DOM
  outer.parentNode.removeChild(outer);

  return scrollbarWidth;
}

// Apply padding-right to body when scrollbar is visible
window.addEventListener('resize', function () {
  if (isScrollbarVisible(document.body)) {
    document.body.style.paddingRight = getScrollbarWidth() + 'px';
  } else {
    document.body.style.paddingRight = '0px';
  }
});
