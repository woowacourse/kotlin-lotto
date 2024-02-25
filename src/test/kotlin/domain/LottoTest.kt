package domain

import domain.model.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {

    @Test
    fun `로또 번호가 5개 일 때 예외를 던지는 지`() {
        val lottoNumbers = listOf(1, 2, 3, 4, 5)
        val lottery = lottoNumbers.map { LottoNumber(it) }.toSet()

        val exception = assertThrows<IllegalArgumentException> { Lotto(lottery) }

        assertThat(exception.message).isEqualTo("로또 번호는 고유한 6개여야 합니다.")
    }

    @Test
    fun `로또 번호가 서로 중복될 때 예외를 던진다`() {
        val lottoNumbers = listOf(1, 2, 3, 4, 5, 5)
        val lottery = lottoNumbers.map { LottoNumber(it) }.toSet()

        val exception = assertThrows<IllegalArgumentException> { Lotto(lottery) }

        assertThat(exception.message).isEqualTo("로또 번호는 고유한 6개여야 합니다.")
    }

    @Test
    fun `로또 숫자 개수가 6개인지 확인한다`() {
        val lottoSize = 6

        val expected = Lotto.makeRandomLotto(1..6).numbers.size

        assertThat(expected).isEqualTo(lottoSize)
    }

    @Test
    fun `결과 로또가 오름차순인지 확인한다`() {
        val actual = setOf(
            LottoNumber(1),
            LottoNumber(2),
            LottoNumber(3),
            LottoNumber(4),
            LottoNumber(5),
            LottoNumber(6)
        )

        val expected = Lotto.makeRandomLotto(1..6).numbers

        assertThat(expected).isEqualTo(actual)
    }
}
