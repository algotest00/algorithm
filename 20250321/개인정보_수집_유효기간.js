// 개인정보 수집 유효기간
const solution = (today, terms, privacies) => {
    const result = [];
    const termsMap = {};

    // 1. 약관별 유효기간(terms)을 객체로 만들기
    terms.forEach(v => {
        const [key, value] = v.split(' ');
        termsMap[key] = Number(value);
    });

    const todayValue = getDateValue(today);

    // 3. privacies 배열을 순회하며 유효기간 계산
    privacies.forEach((privacy, index) => {
        const [date, termType] = privacy.split(' ');
        const dateValue = getDateValue(date) + termsMap[termType] * 28;

        if (dateValue <= todayValue) {
            result.push(index + 1); // 파기해야 할 데이터의 번호 추가
        }
    });

    return result;
}

const getDateValue = (date) => {
    const [year, month, day] = date.split('.').map(Number);
    return year * 12 * 28 + month * 28 + day; // 모든 날짜를 일 단위로 변환
};