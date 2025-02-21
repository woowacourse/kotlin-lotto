package lotto.domain

// 현재 List 컬렉션에 의존하는 로직을 다 빼기는 하였다.
// 하지만 테스트 코드 작성 시 Iterable을 구현해야 테스트 코드의 검증도 다 내부적으로 숨길 수 있다
// 이것까지 숨기는 것은 너무 나간 것일까..?
data class Lotto(
    val value: List<Int>,
) {
    fun getCountOfMatchWith(contrast: Lotto): Int = value.count { it in contrast.value }

    fun contains(element: Int): Boolean = element in value
}
