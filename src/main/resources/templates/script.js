const getButton = document.getElementById('get');
const multiInput = document.querySelector('multi-input');
const values = document.querySelector('#values');

getButton.onclick = () => {
  if (multiInput.getValues().length > 0) {

  //  values.textContent = `Got ${multiInput.getValues().join(' and ')}!`;

  var stack= document.getElementById('stack');
  stack.value= multiInput.getValues();

  alert("사용 스택이 등록되었습니다.");
  //셀렉트 해주는 친구


 //  multiInput.getValues();
  } else {
   // values.textContent = '아무것도 선택하지 않음';
   alert("아무것도 선택하지 않음");
  }
}

//document.querySelector('input').focus();
