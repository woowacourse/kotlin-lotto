package lotto.model

import lotto.domain.model.Lotto
import lotto.domain.model.LottoCreationResult
import lotto.domain.model.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

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

    @Test
    fun `로또는 내가 가진 번호중 몇개가 일치하는지 알 수 있다`() {
        val lotto = createLotto(listOf(1, 2, 3, 4, 5, 6))
        val lotto2 = createLotto(listOf(1, 2, 3, 4, 5, 7))
        assertThat(lotto.countMatchNumbers(lotto2)).isEqualTo(5)
    }

    @Test
    fun `로또는 특정 로또번호가 포함하는지 확인할 수 있다`() {
        val lotto = createLotto(listOf(1, 2, 3, 4, 5, 6))
        val lottoNumber = LottoNumber.valueOf(6)
        val lottoNumber2 = LottoNumber.valueOf(7)
        assertAll(
            { assertThat(lotto.findNumber(lottoNumber)).isEqualTo(true) },
            { assertThat(lotto.findNumber(lottoNumber2)).isEqualTo(false) },
        )
    }

    private fun createLotto(numberList: List<Int>): Lotto =
        when (val result = Lotto.valueOf(numberList.map { LottoNumber.valueOf(it) })) {
            is LottoCreationResult.Success -> result.lotto
            is LottoCreationResult.Failure -> throw IllegalArgumentException("잘못된 테스트 입력입니다.")
        }
}
