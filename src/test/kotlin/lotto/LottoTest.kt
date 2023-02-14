package lotto

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class LottoTest {
    @ParameterizedTest
    @MethodSource("matchLottoNumbers")
    fun `일치하는 번호 개수에 따라 Rank를 구한다`(numbers: List<Int>, result: Rank) {
        val winning = WinningLotto(listOf(1, 2, 3, 4, 5, 6), 7)
        assertThat(
            Lotto(numbers).getCountOfMatch(winning)
        ).isEqualTo(result)
    }

    companion object {
        @JvmStatic
        private fun matchLottoNumbers(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    listOf(1, 2, 3, 9, 10, 11), Rank.FIFTH
                ),
                Arguments.of(
                    listOf(1, 2, 3, 4, 5, 6), Rank.FIRST
                ),
                Arguments.of(
                    listOf(1, 2, 3, 4, 5, 9), Rank.THIRD
                ),
                Arguments.of(
                    listOf(1, 2, 3, 4, 5, 7), Rank.SECOND
                )
            )
        }
    }
}
