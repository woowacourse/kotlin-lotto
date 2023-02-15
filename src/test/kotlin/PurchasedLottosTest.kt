import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PurchasedLottosTest {

    @Test
    fun `구입한 로또 전체의 당첨 결과를 확인한다`() {
        val purchasedLottos = PurchasedLottos(
            listOf(
                Lotto(setOf(1, 2, 3, 4, 5, 6)),
                Lotto(setOf(1, 2, 3, 4, 5, 7)),
                Lotto(setOf(1, 2, 3, 4, 8, 9))
            )
        )

        assertThat(
            purchasedLottos.getTotalLottoResults(WinningNumbers(setOf(1, 2, 3, 4, 5, 6), 7))
        ).isEqualTo(
            listOf(
                LottoResult.FIRST,
                LottoResult.SECOND,
                LottoResult.FORTH
            )
        )
    }
}
