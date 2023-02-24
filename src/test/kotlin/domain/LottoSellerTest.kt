package domain

import common.convertToLotto
import common.convertToLottoNumberSet
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoSellerTest {
    @Test
    fun `로또를 한 장 발급한다`() {
        val money = PurchaseLottoMoney(1 * 1000)
        val lottoSeller = LottoSeller(TestNumberGenerator())
        val lotto = lottoSeller.sellAutoTicket(money)
        assertThat(lotto.lottos.size).isEqualTo(1)
        assertThat(lotto.lottos[0].numbers).isEqualTo(setOf(1, 2, 3, 4, 5, 6).convertToLottoNumberSet())
    }

    @Test
    fun `주어진 수동발급요청로또의 개수와 실제 구매 정보가 다르다면 null을 리턴한다`() {
        val money = PurchaseLottoMoney(4 * 1000)
        val purchaseInfo = LottoPurchaseInfo(money, 3)
        val lottoSeller = LottoSeller(TestNumberGenerator())
        val requestManualNumbers = listOf(
            setOf(11, 12, 13, 14, 15, 16).convertToLotto(),
            setOf(21, 22, 23, 24, 25, 26).convertToLotto()
        )
        assertThat(true).isEqualTo(lottoSeller.sellManualAndAuto(purchaseInfo, Ticket(requestManualNumbers)) == null)
    }

    @Test
    fun `주어진 구매 개수 정보와 요청한 수동 발급 로또의 번호를 토대로 로또를 수동 및 자동 발급 해준다`() {
        val money = PurchaseLottoMoney(4 * 1000)
        val purchaseInfo = LottoPurchaseInfo(money, 2)
        val generator = TestNumberGenerator()
        val lottoSeller = LottoSeller(generator)
        val requestManualNumbers = listOf(
            setOf(11, 12, 13, 14, 15, 16).convertToLotto(),
            setOf(21, 22, 23, 24, 25, 26).convertToLotto()
        )
        val result: Ticket = lottoSeller.sellManualAndAuto(purchaseInfo, Ticket(requestManualNumbers))!!
        assertThat(result.lottos.size).isEqualTo(4)
        assertThat(result.lottos[0].numbers).containsAll(setOf(11, 12, 13, 14, 15, 16).convertToLottoNumberSet())
        assertThat(result.lottos[1].numbers).containsAll(setOf(21, 22, 23, 24, 25, 26).convertToLottoNumberSet())
        assertThat(result.lottos[2].numbers).containsAll(generator.pattern[0].map { LottoNumber.from(it) })
        assertThat(result.lottos[3].numbers).containsAll(generator.pattern[1].map { LottoNumber.from(it) })
    }

    @ParameterizedTest(name = "{0}개의 로또를 발급한다.")
    @ValueSource(ints = [2, 3, 4])
    fun `입력받은 개수만큼 로또를 발급한다`(count: Int) {
        val money = PurchaseLottoMoney(count * 1000)
        val generator = TestNumberGenerator()
        val lottoSeller = LottoSeller(generator)
        val ticket = lottoSeller.sellAutoTicket(money)
        assertThat(ticket.lottos.map { lotto -> lotto.numbers }).isEqualTo(
            generator.pattern.map { it.convertToLottoNumberSet() }.take(count)
        )
    }

    inner class TestNumberGenerator : RandomGenerator {
        val pattern = listOf(
            setOf(1, 2, 3, 4, 5, 6),
            setOf(9, 8, 7, 6, 5, 4),
            setOf(45, 30, 27, 1, 2, 7),
            setOf(5, 8, 9, 2, 10, 17)
        )
        private var i = 0

        override fun generate(): Set<Int> {
            return pattern[i++]
        }
    }
}
