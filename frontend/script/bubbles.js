
const elem = selector => document.querySelector(selector);

const bubble = elem(".bubble");

window.addEventListener("DOMContentLoaded", () => {
  const x = 400; // Set default x position
  const y = 100; // Set default y position

  let bubbleGroup = document.createElement("ul");
  bubbleGroup.style.left = x + "px";
  bubbleGroup.style.top = y + "px";
  for (let i = 0; i < 4; i++) {
    let bb = document.createElement("li");
    bubbleGroup.appendChild(bb);
  }
  bubble.appendChild(bubbleGroup);
});