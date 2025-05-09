function solution(edges) {
    const inOutMap = new Map();

    // 1. 각 노드의 out-degree, in-degree 계산
    for (const [from, to] of edges) {
        // 각 노드에 대해 [out-degree, in-degree]를 저장
        if (!inOutMap.has(from)) inOutMap.set(from, [0, 0]);
        if (!inOutMap.has(to)) inOutMap.set(to, [0, 0]);

        // from은 나가는 간선이므로 out-degree 증가
        inOutMap.get(from)[0] += 1;
        // to는 들어오는 간선이므로 in-degree 증가
        inOutMap.get(to)[1] += 1;
    }

    let createdNode = 0; // 생성된 중앙 노드
    let donut = 0;       // 도넛 그래프 수
    let stick = 0;       // 막대 그래프 수
    let eight = 0;       // 8자 그래프 수

    // 2. 각 노드를 순회하면서 생성 정점과 그래프 유형을 분류
    for (const [node, [out, inn]] of inOutMap.entries()) {
        if (out >= 2 && inn === 0) {
            // 생성 정점은: 나가는 간선이 2개 이상, 들어오는 간선이 없음
            createdNode = node;
        } else if (out === 0) {
            // 막대 그래프의 끝 노드는 나가는 간선이 없음
            stick += 1;
        } else if (out >= 2 && inn >= 2) {
            // 8자 그래프의 중심 노드는 in/out이 모두 2 이상
            eight += 1;
        }
    }

    // 3. 도넛 그래프 수 계산
    // 생성 정점에서 나간 총 간선 수 - 막대 수 - 8자 수
    donut = (inOutMap.get(createdNode)?.[0] || 0) - stick - eight;

    return [createdNode, donut, stick, eight];
}
