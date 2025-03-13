## str to int
### 문자열 정수 변환하기
#### 여러가지 방법 ~

1. Number 사용
```js
const solution = (s) => {
    return Number(s);
}

const ans = solution("1234");
console.log(typeof ans); // number
```
`new Number()` 과 같이 숫자객체를 생성하는 생성자로도 활용할 수 있지만, 일반적으로는 형변환 함수로 더 잘 이용됨

2. parseInt 사용
```js
const solution = (s) => {
    return Number(s);
}

const ans = solution("5678");
console.log(typeof ans); // number
```
parseInt는 문자열을 파싱해서 숫자를 찾는 기능

1. 문자열에 수학연산하기
```js
const solution = (s) => {
    return s * 1;
}

const ans = solution("20");
console.log(typeof ans); // number
```
문자열과 숫자의 연산 시 묵시적 변환으로 문자열이 숫자로 변환되어 계산된다.

---
<br/>

* Number 와 parseInt의 차이점

**Number**는 주어진 값 전체를 숫자로 바꾸려고 시도하며, 실수도 변환 가능하다.
```js
Number('20.2'); // 20.2
Number(''); // 0
Number(null); // 0
Number('100a') // NaN
```
또한 빈 문자열과 null은 0을 반환하며, 완전히 숫자가 아닌 문자열은 변환하지 못한다.

<br/>

**parseInt**는 정수만을 추출한다.
```js
parseInt('20.7'); // 20
parseInt('123abc'); // 123
parseInt('abc123'); // NaN 
```
실수의 경우에는 소수점 아래는 버리고,
문자열과 숫자가 섞인 경우에는 앞에서부터 순서대로 숫자로 변환하다가 숫자가 아닌 문자열을 만나면 중단하게 된다.