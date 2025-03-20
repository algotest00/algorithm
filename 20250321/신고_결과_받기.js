// 신고 결과 받기
function solution(id_list, report, k) {
    var answer = [];
    // id_list : 유저 id 리스트
    // report : 신고 리포트
    // k : 정지당할 신고 횟수

    // 1. report 중복 제거
    // -> 중복된 신고는 허용하지 않음
    const uniqueReport = [...new Set(report)];
    console.log(uniqueReport);

    // 2. 유저별로 몇 번 신고 당했는지 저장할 객체 생성
    const reportCount = {};
    const userReports = {};
    id_list.forEach(id => {
        reportCount[id] = 0;
        userReports[id] = []; // 메일 발송해야 하니까 누가 누굴 신고했는지 알아야함
    });

    // 3. 신고결과 저장하기
    uniqueReport.forEach(rep => {
        const [user, target] = rep.split(" ");
        userReports[user].push(target);
        reportCount[target]++;
    });

    // 4. 정지된 유저 찾기
    const banUser = new Set(
        id_list.filter(id => reportCount[id] >= k)
    );

    // 5. 유저별로 메일 발송 횟수 구하기
    return id_list.map(user =>
        userReports[user].filter(target => banUser.has(target)).length
    );
}

console.log(solution(["muzi", "frodo", "apeach", "neo"], ["muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"], 2));