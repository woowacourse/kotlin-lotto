package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class LottoAdministratorTest {

    @MethodSource("provideNumber")
    @ParameterizedTest
    fun `당첨번호와 몇개 일치하는지 확인한다`(lottoNumber: List<Int>, winningNumber: List<Int>, matchCount: Int) {
        // when
        val actual = LottoAdministrator().compareWinningNumber(lottoNumber, winningNumber)
        // then
        assertThat(actual).isEqualTo(matchCount)
    }

    @Test
    fun `보너스번호와 몇개 일치하는지 확인한다`() {
        //given
        val lottoNumber = listOf(1, 2, 3, 4, 5, 6)
        val bonusNumber = 5

        //when
        val actual = LottoAdministrator().compareBonusNumber(lottoNumber, bonusNumber)
        //then
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