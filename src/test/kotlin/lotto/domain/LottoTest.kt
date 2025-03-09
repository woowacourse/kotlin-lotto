package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    fun createLotto(vararg numbers: Int): Lotto {
        return Lotto.of(setOf(*numbers.map { LottoNumber.from(it) }.toTypedArray())) ?: error("로또 생성 실패")
    }

    @Test
    fun `로또는 중복되지 않는 6개의 숫자를 갖는다`() {
        val lotto = createLotto(1, 2, 3, 4, 5, 6)
        assertThat(lotto.lotto.size).isEqualTo(6)
    }

    @Test
    fun `로또는 중복된 숫자를 가지면 예외 발생 여부를 확인한다`() {
        assertThrows<IllegalArgumentException> { createLotto(1, 1, 2, 3, 4, 5) }
    }

    @Test
    fun `로또 번호는 오름차순으로 정렬된다`() {
        val lotto = createLotto(7, 2, 3, 4, 5, 6)
        val actual = createLotto(2, 3, 4, 5, 6, 7)
        assertThat(lotto).isEqualTo(actual)
    }
}
