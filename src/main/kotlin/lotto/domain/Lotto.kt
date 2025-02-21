package lotto.domain

import lotto.global.LottoValidator.requireValidLotto

// 현재 List 컬렉션에 의존하는 로직을 다 빼기는 하였다.
// 하지만 테스트 코드 작성 시 Iterable을 구현해야 테스트 코드의 검증도 다 내부적으로 숨길 수 있다
// 이것까지 숨기는 것은 너무 나간 것일까..?
// 그리고 애초에 생성자로 List를 받고 있는데 이걸 굳이 일급 컬렉션으로 만들 의미가 있을까?
data class Lotto(
    val value: List<Int>,
) {
    init {
        requireValidLotto(value)
    }

    fun getCountOfMatchWith(contrast: Lotto): Int = value.count { it in contrast.value }

    fun contains(element: Int): Boolean = element in value
}
