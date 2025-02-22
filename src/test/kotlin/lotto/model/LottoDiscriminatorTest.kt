package lotto.model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class LottoDiscriminatorTest {
    @ParameterizedTest
    @MethodSource("lottoCasesForResultTest")
    fun `각 로또는 해당되는 당첨 번호에 맞게 랭크가 반환된다`(
        lottoNumbers: List<Int>,
        expectedRank: Rank,
    ) {
        val winningLotto = Lotto.from(listOf(1, 2, 3, 4, 5, 6))
        val bonusNumber = LottoNumber(7)

        val lottoDiscriminator = LottoDiscriminator(winningLotto, bonusNumber)
        val lotto = Lotto.from(lottoNumbers)
        val rank = lottoDiscriminator.discriminateLotto(lotto)

        Assertions.assertEquals(expectedRank, rank)
    }

    @Test
    fun `보너스 번호가 일치하면서 5개의 번호가 동일한 경우 2등을 반환한다`() {
        val winningLotto = Lotto.from(listOf(1, 2, 3, 4, 5, 6))
        val bonusNumber = LottoNumber(7)

        val lottoDiscriminator = LottoDiscriminator(winningLotto, bonusNumber)
        val existingLotto = Lotto.from(listOf(1, 2, 3, 4, 5, 7))
        val rank = lottoDiscriminator.discriminateLotto(existingLotto)

        Assertions.assertEquals(Rank.SECOND, rank)
    }

    @Test
    fun `당첨 번호는 중복되지 않았을 때, 보너스 번호와 중복되면 오류를 반환한다`() {
        val winningLotto = Lotto.from(listOf(1, 2, 3, 4, 5, 6))
        val bonusNumber = LottoNumber(1)

        assertThrows<IllegalArgumentException> {
            LottoDiscriminator(winningLotto, bonusNumber)
        }
    }

    companion object {
        @JvmStatic
        fun lottoCasesForResultTest(): Stream<Arguments> =
            Stream.of(
                Arguments.of(listOf(1, 2, 3, 4, 5, 6), Rank.FIRST),
                Arguments.of(listOf(1, 2, 3, 4, 5, 8), Rank.THIRD),
                Arguments.of(listOf(1, 2, 3, 4, 9, 10), Rank.FOURTH),
                Arguments.of(listOf(1, 2, 3, 8, 9, 10), Rank.FIFTH),
                Arguments.of(listOf(1, 2, 23, 24, 25, 26), Rank.MISS),
                Arguments.of(listOf(1, 22, 23, 24, 25, 26), Rank.MISS),
                Arguments.of(listOf(21, 22, 23, 24, 25, 26), Rank.MISS),
            )
    }
}
