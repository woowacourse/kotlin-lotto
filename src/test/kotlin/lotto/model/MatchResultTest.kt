package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MatchResultTest {
    @Test
    fun `5개의 당첨 번호가 일치하고 보너스 번호가 일치하지 않으면 3등이고, 일치하면 2등이다`() {
        val lottoNumbers =
            listOf(
                Lotto(
                    setOf(
                        LottoNumber(1),
                        LottoNumber(2),
                        LottoNumber(3),
                        LottoNumber(4),
                        LottoNumber(5),
                        LottoNumber(6),
                    ),
                ),
                Lotto(
                    setOf(
                        LottoNumber(1),
                        LottoNumber(2),
                        LottoNumber(3),
                        LottoNumber(4),
                        LottoNumber(5),
                        LottoNumber(8),
                    ),
                ),
            )
        val winningLottoNumbers =
            Lotto(
                setOf(
                    LottoNumber(1),
                    LottoNumber(2),
                    LottoNumber(3),
                    LottoNumber(4),
                    LottoNumber(5),
                    LottoNumber(7),
                ),
            )
        val lottoResult =
            MatchResult(
                LottoBundle(lottoNumbers),
                DrawResult(winningLottoNumbers, LottoNumber.from("8")),
            ).calculateResult()

        assertThat(lottoResult.resultMap).isEqualTo(mapOf(Rank.THIRD to 1, Rank.SECOND to 1))
    }
}
