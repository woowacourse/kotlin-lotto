package lotto.model

import lotto.domain.model.Lotto
import lotto.domain.model.LottoCreationResult
import lotto.domain.model.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `로또 번호 개수가 6개가 아닐 경우 null을 반환한다`() {
        val lotto = createLotto(listOf(1, 2, 3, 4, 5))
        assertThat(lotto).isNull()
    }

    @Test
    fun `로또 번호에 중복이 있을 경우 null을 반환한다`() {
        val lotto = createLotto(listOf(1, 2, 3, 4, 5, 1))
        assertThat(lotto).isNull()
    }

    @Test
    fun `랜덤 생성 시 1부터 45 사이의 값으로 로또가 생성된다`() {
        val lotto = Lotto.createRandom()
        assertThat(lotto.numberList.all { it.value in 1..45 }).isTrue()
    }

    private fun createLotto(numberList: List<Int>): Lotto? {
        return when (val result = Lotto.valueOf(numberList.map { LottoNumber.valueOf(it) })) {
            is LottoCreationResult.Success -> result.lotto
            is LottoCreationResult.Failure -> null
        }
    }

}
