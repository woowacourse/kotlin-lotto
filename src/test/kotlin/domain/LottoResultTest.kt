package domain

import domain.Rank.FIFTH
import domain.Rank.FIRST
import domain.Rank.FOURTH
import domain.Rank.MISS
import domain.Rank.SECOND
import domain.Rank.THIRD
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test

class LottoResultTest {
    @Test
    fun `로또 수익률을 반환한다`() {
        val lottoResult = LottoResult(
            mapOf(
                FIRST to 1,
                SECOND to 1,
                THIRD to 0,
                FOURTH to 1,
                FIFTH to 0,
                MISS to 0,
            ),
        )
        val result = lottoResult.getRateOfReturn()
        val expect = (FIRST.winningMoney.toDouble() + SECOND.winningMoney + FOURTH.winningMoney) / (lottoResult.values.sum() * LottoStore.LOTTO_PRICE)

        assertThat(result).isEqualTo(expect)
    }

    @Test
    fun `로또가 하나도 안들어오면 오류가 발생한다`() {
        assertThatIllegalArgumentException()
            .isThrownBy {
                LottoResult(
                    mapOf(
                        FIRST to 0,
                        SECOND to 0,
                        THIRD to 0,
                        FOURTH to 0,
                        FIFTH to 0,
                        MISS to 0,
                    ),
                )
            }
            .withMessage("적어도 하나 이상의 복권을 넣어야합니다.")
    }
}
