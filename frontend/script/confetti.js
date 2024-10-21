function createConfetti() {
  const confettiContainer = document.getElementById('confettiContainer');

  for (let i = 0; i < 50; i++) { // Number of confetti pieces
      const confetti = document.createElement('div');
      confetti.classList.add('confetti');

      // Randomize confetti color
      confetti.style.setProperty('--confetti-color', getRandomColor());

      // Randomize confetti position and animation duration
      confetti.style.left = `${Math.random() * 100}vw`;
      confetti.style.animationDuration = `${Math.random() * 3 + 2}s`; // Between 2 and 5 seconds

      confettiContainer.appendChild(confetti);
  }
}

function getRandomColor() {
  const colors = ['#FFC107', '#FF5722', '#4CAF50', '#03A9F4', '#9C27B0', '#FFEB3B'];
  return colors[Math.floor(Math.random() * colors.length)];
}

createConfetti();