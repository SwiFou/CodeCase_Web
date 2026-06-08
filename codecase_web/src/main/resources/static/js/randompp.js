const myDiv = document.querySelector('.avatar');
const randomId = Math.floor(Math.random() * 12); //
const imageUrl = `url(../images/avatars/Avatars-${randomId}.svg)`;
myDiv.style.setProperty('--avatar-url', imageUrl);