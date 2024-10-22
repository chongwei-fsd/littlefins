const text=document.getElementById('typingEffect').getAttribute('data-strings')

const options = {
    strings: [text],
    typeSpeed: 10, //Typing speed in milliseconds
    backSpeed: 0, //Backspacing speed in milliseconds
    // typeSpeed: 30, 
    // backSpeed: 20, 
    loop: false,
    showCursor: false
};
new Typed('#typingEffect', options);
