const getButton = document.getElementById('get');
const multiInput = document.querySelector('multi-input'); 
const values = document.querySelector('#values'); 

getButton.onclick = () => {
  if (multiInput.getValues().length > 0) {
  
  //  values.textContent = `Got ${multiInput.getValues().join(' and ')}!`;
  
  console.log(multiInput.getValues());
  } else {
   // values.textContent = '아무것도 선택하지 않음'; 
   console.log("아무것도 선택하지 않음");
  }
}

//document.querySelector('input').focus();
