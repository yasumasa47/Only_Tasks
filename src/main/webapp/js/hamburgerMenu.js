const hamburger = document.querySelector('.hamburger-menu');
const side = document.querySelector('.side');

hamburger.addEventListener('click', () => {
  hamburger.classList.toggle('active');
  side.classList.toggle('active');
});

side.addEventListener('click', (e) => {
  if (e.target.classList.contains('side')) {
    hamburger.classList.remove('active');
    side.classList.remove('active');
  }
});
