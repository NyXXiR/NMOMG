const getButton = document.getElementById('get');
const multiInput = document.querySelector('multi-input');
const values = document.querySelector('#values');

getButton.onclick = () => {
  if (multiInput.getValues().length > 0) {

  //  values.textContent = `Got ${multiInput.getValues().join(' and ')}!`;


const multiInput = document.querySelector('multi-input'); 
const values = document.querySelector('#values'); 

getButton.onclick = () => {
  if (multiInput.getValues().length > 0) {
    var stack = document.getElementById('stack');
    stack.value = multiInput.getValues();

    alert("사용 스택이 등록되었습니다.");

  } else {
    alert("아무것도 선택하지 않음");
  }
}
}