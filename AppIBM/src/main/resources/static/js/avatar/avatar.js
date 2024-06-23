// Get canvas and context
const canvas = document.getElementById('avatarCanvas');
const ctx = canvas.getContext('2d');

// Retrieve previous skin color, eye color, hair type, hair color, and nose size from sessionStorage
let skinColor = sessionStorage.getItem('skinColor') || "#ffddb3"; // Default to light
let eyeColor = sessionStorage.getItem('eyeColor') || "#66533d"; // Default to brown
let hairType = sessionStorage.getItem('hairType') || "curly"; // Default to curly
let hairColor = sessionStorage.getItem('hairColor') || "black"; // Default to black
let noseSize = sessionStorage.getItem('noseSize') || "medium"; // Default to medium
let mouthSize = sessionStorage.getItem('mouthSize') || "medium"; // Default to medium
let glasses = false; // Default to no glasses

// Function to draw the avatar
function drawAvatar() {
  ctx.clearRect(0, 0, canvas.width, canvas.height);

  // Draw head with corresponding skin color
  ctx.fillStyle = skinColor;
  ctx.beginPath();
  ctx.arc(100, 100, 70, 0, Math.PI * 2);
  ctx.fill();

  // Draw ears with corresponding skin color
  ctx.fillStyle = skinColor;
  // Left ear
  ctx.beginPath();
  ctx.arc(35, 100, 12, 0, Math.PI * 2);
  ctx.fill();
  // Right ear
  ctx.beginPath();
  ctx.arc(165, 100, 12, 0, Math.PI * 2);
  ctx.fill();

  // Draw eyes with corresponding eye color
  ctx.fillStyle = "#ffffff"; // White iris
  ctx.beginPath();
  ctx.arc(80, 90, 10, 0, Math.PI * 2); // Left eye
  ctx.arc(120, 90, 10, 0, Math.PI * 2); // Right eye
  ctx.fill();

  // Draw pupils (same color as the eye color)
  ctx.fillStyle = eyeColor; // Pupil color matches eye color
  ctx.beginPath();
  ctx.arc(80, 90, 5, 0, Math.PI * 2); // Left pupil
  ctx.arc(120, 90, 5, 0, Math.PI * 2); // Right pupil
  ctx.fill();

  // Draw nose based on selected size
  if (noseSize === "small") {
    ctx.beginPath();
    ctx.arc(100, 115, 5, 0, Math.PI * 2); // Small nose
    ctx.lineWidth = 1; // Set a fixed width for the outline
    ctx.strokeStyle = "#000000"; // Black outline
    ctx.stroke(); // Draw the outline
  } else if (noseSize === "medium") {
    ctx.beginPath();
    ctx.arc(100, 115, 8, 0, Math.PI * 2); // Medium nose
    ctx.lineWidth = 1; // Set a fixed width for the outline
    ctx.strokeStyle = "#000000"; // Black outline
    ctx.stroke(); // Draw the outline
  } else if (noseSize === "big") {
    ctx.beginPath();
    ctx.arc(100, 115, 12, 0, Math.PI * 2); // Big nose
    ctx.lineWidth = 1; // Set a fixed width for the outline
    ctx.strokeStyle = "#000000"; // Black outline
    ctx.stroke(); // Draw the outline
  }

  // Draw mouth based on selected size
  ctx.fillStyle = "#f00"; // Red color for the mouth
  ctx.lineWidth = 1; // Set a fixed width for the outline
  if (mouthSize === "small") {
    ctx.beginPath();
    ctx.arc(100, 130, 15, 0.2 * Math.PI, 0.8 * Math.PI); // Small mouth (smile)
    ctx.stroke();
  } else if (mouthSize === "medium") {
    ctx.beginPath();
    ctx.arc(100, 130, 20, 0.2 * Math.PI, 0.8 * Math.PI); // Medium mouth (smile)
    ctx.stroke();
  } else if (mouthSize === "big") {
    ctx.beginPath();
    ctx.arc(100, 130, 25, 0.2 * Math.PI, 0.8 * Math.PI); // Big mouth (smile)
    ctx.stroke();
  }

  // Draw glasses if selected
  if (glasses) {
    ctx.strokeStyle = "#000000"; // Black
    ctx.lineWidth = 1; // Set line width to 1

    // Draw left lens
    ctx.beginPath();
    ctx.arc(80, 90, 15, 0, Math.PI * 2); // Draw left lens as a circle, moved down by 5 units
    ctx.stroke(); // Draw the left lens

    // Draw right lens
    ctx.beginPath();
    ctx.arc(120, 90, 15, 0, Math.PI * 2); // Draw right lens as a circle, moved down by 5 units
    ctx.stroke(); // Draw the right lens

    // Draw the bridge between the lenses
    ctx.beginPath();
    ctx.moveTo(95, 90); // Move to the start of the bridge, moved down by 5 units
    ctx.lineTo(105, 90); // Draw a line to the end of the bridge, moved down by 5 units
    ctx.stroke(); // Draw the bridge

    // Draw glasses arms
    ctx.lineWidth = 1; // Set line width to 1
    ctx.beginPath();
    ctx.moveTo(30, 90); // Left arm start, slightly moved up to match new lens position
    ctx.lineTo(65, 90); // Left arm end, slightly moved up to match new lens position
    ctx.stroke(); // Draw left arm

    ctx.beginPath();
    ctx.moveTo(135, 90); // Right arm start, slightly moved up to match new lens position
    ctx.lineTo(170, 90); // Right arm end, slightly moved up to match new lens position
    ctx.stroke(); // Draw right arm
  }

  // Draw hair based on hairType and hairColor
  if (hairType === "short") {
    drawShortHair();
  } else if (hairType === "long") {
    drawLongHair();
  } else if (hairType === "curly") {
    drawCurlyHair();
  } else if (hairType === "bald") {
    // Handle bald hair type
  } else {
    // Handle unknown hairType or other cases
    console.error("Unknown hairType:", hairType);
  }
}

// Function to draw short hair
function drawShortHair() {
  // Draw short hair (buzzcut)
  ctx.fillStyle = hairColor; // Set hair color
  ctx.beginPath();
  ctx.moveTo(35, 30); // Start from top left of head
  ctx.lineTo(165, 30); // Draw a straight line to the top right of the head
  ctx.lineTo(165, 60); // Extend hair strands down on the right side
  ctx.quadraticCurveTo(100, 80, 35, 60); // Draw a curve for the hair strands at the back
  ctx.lineTo(35, 30); // Draw a straight line to the top left of the head to close the shape
  ctx.fill();

  // Draw right hair strand
  ctx.beginPath();
  ctx.moveTo(170, 30); // Start from top right of head
  ctx.lineTo(170, 90); // Draw a vertical line down
  ctx.lineTo(160, 90); // Draw a horizontal line to the left
  ctx.lineTo(160, 80); // Draw a vertical line up
  ctx.lineTo(100, 30); // Draw a diagonal line to connect to the top middle of the head
  ctx.closePath(); // Close the path
  ctx.fill(); // Fill the right hair strand with the selected color

  // Draw left hair strand
  ctx.beginPath();
  ctx.moveTo(30, 30); // Start from top left of head
  ctx.lineTo(30, 90); // Draw a vertical line down
  ctx.lineTo(40, 90); // Draw a horizontal line to the right
  ctx.lineTo(40, 80); // Draw a vertical line up
  ctx.lineTo(100, 30); // Draw a diagonal line to connect to the top middle of the head
  ctx.closePath(); // Close the path
  ctx.fill(); // Fill the left hair strand with the selected color
}

// Function to draw long hair
function drawLongHair() {
  ctx.fillStyle = hairColor; // Set hair color

  // Draw left hair strand
  ctx.beginPath();
  ctx.moveTo(20, 20); // Start from top left of head
  ctx.quadraticCurveTo(10, 40, 20, 60); // Draw a curve for the left hair strand, adjusting control points for curvature
  ctx.lineTo(20, 200); // Draw a vertical line down
  ctx.lineTo(40, 200); // Draw a horizontal line to the right
  ctx.lineTo(40, 80); // Draw a vertical line up
  ctx.lineTo(100, 20); // Draw a diagonal line to connect to the top middle of the head
  ctx.closePath(); // Close the path
  ctx.fill(); // Fill the left hair strand with the selected color

  // Draw right hair strand
  ctx.beginPath();
  ctx.moveTo(180, 20); // Start from top right of head
  ctx.quadraticCurveTo(190, 40, 180, 60); // Draw a curve for the right hair strand, adjusting control points for curvature
  ctx.lineTo(180, 200); // Draw a vertical line down
  ctx.lineTo(160, 200); // Draw a horizontal line to the left
  ctx.lineTo(160, 80); // Draw a vertical line up
  ctx.lineTo(100, 20); // Draw a diagonal line to connect to the top middle of the head
  ctx.closePath(); // Close the path
  ctx.fill(); // Fill the right hair strand with the selected color

  // Draw middle hair strand (horizontal)
  ctx.beginPath();
  ctx.moveTo(20, 20); // Start from top left of head
  ctx.quadraticCurveTo(50, 5, 100, 10); // Adjusted curve for the top left corner
  ctx.lineTo(160, 10); // Draw a horizontal line to the right
  ctx.quadraticCurveTo(180, 15, 180, 20); // Adjusted curve for the top right corner
  ctx.lineTo(140, 50); // Draw a vertical line down
  ctx.lineTo(40, 50); // Draw a horizontal line to the left
  ctx.closePath(); // Close the path
  ctx.fill(); // Fill the middle hair strand with the selected color
}

// Function to draw curly hair
function drawCurlyHair() {
  // Draw curly hair (quadratic curves)
  ctx.fillStyle = hairColor; // Set hair color
  ctx.beginPath();
  ctx.moveTo(35, 40); // Start from top left of head
  ctx.quadraticCurveTo(30, 10, 80, 20); // Adjusted curve for higher top
  ctx.quadraticCurveTo(100, 10, 120, 20); // Adjusted curve for higher top
  ctx.quadraticCurveTo(140, 10, 165, 35); // Adjusted curve for higher top
  ctx.lineTo(165, 70); // Extend hair down to simulate hairline
  ctx.lineTo(35, 70); // Extend hair down to simulate hairline
  ctx.fill();

  // Draw right hair strand
  ctx.beginPath();
  ctx.moveTo(170, 30); // Start from top right of head
  ctx.lineTo(170, 90); // Draw a vertical line down
  ctx.lineTo(160, 90); // Draw a horizontal line to the left
  ctx.lineTo(160, 80); // Draw a vertical line up
  ctx.lineTo(100, 30); // Draw a diagonal line to connect to the top middle of the head
  ctx.closePath(); // Close the path
  ctx.fill(); // Fill the right hair strand with the selected color

  // Draw left hair strand
  ctx.beginPath();
  ctx.moveTo(30, 30); // Start from top left of head
  ctx.lineTo(30, 90); // Draw a vertical line down
  ctx.lineTo(40, 90); // Draw a horizontal line to the right
  ctx.lineTo(40, 80); // Draw a vertical line up
  ctx.lineTo(100, 30); // Draw a diagonal line to connect to the top middle of the head
  ctx.closePath(); // Close the path
  ctx.fill(); // Fill the left hair strand with the selected color
}

// Function to handle skin color change
function changeSkinColor() {
  skinColor = document.getElementById("skinColor").value;
  drawAvatar(); // Redraw the avatar with the new skin color
  // Store the current state in sessionStorage
  sessionStorage.setItem('skinColor', skinColor);
}

// Function to handle eye color change
function changeEyeColor() {
  eyeColor = document.getElementById("eyeColor").value;
  drawAvatar(); // Redraw the avatar with the new eye color
  // Store the current state in sessionStorage
  sessionStorage.setItem('eyeColor', eyeColor);
}

// Function to handle hair type change
function changeHairType() {
  hairType = document.getElementById("hairType").value;
  drawAvatar(); // Redraw the avatar with the new hair type
  // Store the current state in sessionStorage
  sessionStorage.setItem('hairType', hairType);
}

// Function to handle hair color change
function changeHairColor() {
  hairColor = document.getElementById("hairColor").value;
  drawAvatar(); // Redraw the avatar with the new hair color
  // Store the current state in sessionStorage
  sessionStorage.setItem('hairColor', hairColor);
}

// Function to handle nose size change
function changeNoseSize() {
  noseSize = document.getElementById("noseSize").value;
  drawAvatar(); // Redraw the avatar with the new nose size
  // Store the current state in sessionStorage
  sessionStorage.setItem('noseSize', noseSize);
}

// Function to handle mouth size change
function changeMouthSize() {
  mouthSize = document.getElementById("mouthSize").value;
  drawAvatar(); // Redraw the avatar with the new mouth size
  // Store the current state in sessionStorage
  sessionStorage.setItem('mouthSize', mouthSize);
}

// Function to handle glasses checkbox change
function changeGlasses() {
  glasses = document.getElementById("glassesCheckbox").checked;
  drawAvatar(); // Redraw the avatar with the new glasses information
  // Store the current state in sessionStorage
  sessionStorage.setItem('glasses', glasses);
}

// Function to save the avatar
function saveAvatar() {
  const dataURL = canvas.toDataURL();

  // Convert data URL to Blob
  const blob = dataURItoBlob(dataURL);

  // Create FormData object
  const formData = new FormData();
  formData.append('avatar', blob); // Append the avatar image
  formData.append('skinColor', skinColor); // Append the selected skin color
  formData.append('eyeColor', eyeColor); // Append the selected eye color
  formData.append('hairType', hairType); // Append the selected hair type
  formData.append('hairColor', hairColor); // Append the selected hair color
  formData.append('noseSize', noseSize); // Append the selected nose size
  formData.append('mouthSize', mouthSize); // Append the selected mouth size
  formData.append('glasses', glasses ? 'true' : 'false'); // Append the glasses information as string 'true' or 'false'

  // Obtain the CSRF token from the page's meta tag
  const csrfToken = document.querySelector("meta[name='_csrf']").getAttribute(
      "content");

  // Include CSRF token in FormData
  formData.append('_csrf', csrfToken);

  // Send FormData using fetch
  fetch('/saveAvatar', {
    method: 'POST',
    body: formData
  })
  .then(response => {
    if (response.ok) {
      // Handle success response
      console.log('Avatar saved successfully!');
      alert('Avatar saved successfully!');
      // Redirect to the profile page
      window.location.href = '/profile';
    } else {
      // Handle error response
      console.error('Error saving avatar:', response.statusText);
      alert(`Error saving avatar: ${response.statusText}`);
      // Optional: Redirect to an error page or handle it as needed
    }
  })
  .catch(error => {
    // Handle network or other errors
    console.error('Error saving avatar:', error);
    alert('Error saving avatar. Check the console for details.');
  });

  // Function to convert data URI to Blob
  function dataURItoBlob(dataURI) {
    const byteString = atob(dataURI.split(',')[1]);
    const mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0];
    const ab = new ArrayBuffer(byteString.length);
    const ia = new Uint8Array(ab);
    for (let i = 0; i < byteString.length; i++) {
      ia[i] = byteString.charCodeAt(i);
    }
    return new Blob([ab], {type: mimeString});
  }

}

// Call drawAvatar function when the page loads
drawAvatar();
