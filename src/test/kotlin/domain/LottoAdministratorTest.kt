package domain

import model.BonusNumber
import model.Lotto
import model.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class LottoAdministratorTest {

    @MethodSource("provideNumber")
    @ParameterizedTest
    fun `당첨번호와 몇개 일치하는지 확인한다`(lottoNumber: Lotto, winningNumber: Lotto, matchCount: Int) {
        // when
        val actual = LottoAdministrator().getMatchOfNumber(lottoNumber, winningNumber)
        // then
        assertThat(actual).isEqualTo(matchCount)
    }

    @Test
    fun `보너스번호가 일치하는지 확인한다`() {
        // given
        val lottoNumber = Lotto(
            listOf(
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(3),
                LottoNumber(4),
                LottoNumber(5),
                LottoNumber(6),
            ),
        )
        val bonusNumber = BonusNumber(lottoNumber, LottoNumber(3))

        // when
        val actual = LottoAdministrator().isMatchBonus(lottoNumber, bonusNumber)
        // then
        assertThat(actual).isEqualTo(true)
    }

    companion object {
        @JvmStatic
        fun provideNumber(): List<Arguments> = listOf(
            Arguments.of(listOf(1, 2, 3, 4, 5, 6), listOf(1, 2, 3, 4, 5, 7), 5),
            Arguments.of(listOf(1, 2, 3, 4, 5, 6), listOf(1, 2, 3, 7, 8, 9), 3),
        )
    }
}
