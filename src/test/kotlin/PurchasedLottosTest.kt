import domain.model.LottoResult
import domain.model.WinningNumbers
import domain.model.lotto.Lotto
import domain.model.lotto.LottoNumber
import domain.model.lotto.PurchasedLottos
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PurchasedLottosTest {

    @Test
    fun `구입한 로또 전체의 당첨 결과를 확인한다`() {
        // given
        val purchasedLottos = PurchasedLottos(
            listOf(
                Lotto.create(listOf(1, 2, 3, 4, 5, 6)),
                Lotto.create(listOf(1, 2, 3, 4, 5, 7)),
                Lotto.create(listOf(1, 2, 3, 4, 8, 9))
            )
        )

        val winningNumbers = WinningNumbers(
            setOf(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(4),
                LottoNumber.from(5),
                LottoNumber.from(6)
            ),
            LottoNumber.from(7)
        )
        // when
        val actual = purchasedLottos.getTotalLottoResults(winningNumbers)
        // then
        assertThat(
            actual
        ).isEqualTo(
            listOf(
                LottoResult.FIRST,
                LottoResult.SECOND,
                LottoResult.FORTH
            )
        )
    }
}
