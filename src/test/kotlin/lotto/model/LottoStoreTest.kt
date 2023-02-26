package lotto.model

import lotto.entity.Lotto
import lotto.entity.LottoCount
import lotto.entity.LottoNumber
import lotto.entity.LottoTicket
import lotto.entity.PurchaseMoney
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class LottoStoreTest {

    @Test
    fun `수동 로또 2개를 구매한다`() {
        // given
        val purchaseMoney = PurchaseMoney(2000)
        val lottoA = LottoTicket(listOf(10, 12, 31, 34, 35, 45))
        val lottoB = LottoTicket(listOf(10, 12, 31, 34, 35, 45))
        val purchaseCount = LottoCount(purchaseMoney.getPurchaseLottoCount())
        val store = LottoStore(purchaseCount)
        // when
        val actual = store.buyManualLotto(lottoA, lottoB)
        val expect = listOf(
            Lotto.from(lottoA.numbers.map(::LottoNumber)),
            Lotto.from(lottoB.numbers.map(::LottoNumber)),
        )
        // then
        assertAll({
            assertThat(actual.value.size).isEqualTo(2)
            assertThat(actual.value[0]).isEqualTo(expect[0])
            assertThat(actual.value[1]).isEqualTo(expect[1])
        })
    }

    @Test
    fun `자동 로또 2개를 구매한다`() {
        // given
        val purchaseMoney = PurchaseMoney(2000)
        val purchaseCount = LottoCount(purchaseMoney.getPurchaseLottoCount())
        val store = LottoStore(purchaseCount, lottoGenerator)
        // when
        val actual = store.buyAutoLotto(purchaseCount)
        // then
        assertAll({
            assertThat(actual.value.size).isEqualTo(2)
            assertThat(actual.value[0]).isEqualTo(
                Lotto.from(
                    listOf(
                        LottoNumber(1),
                        LottoNumber(2),
                        LottoNumber(3),
                        LottoNumber(4),
                        LottoNumber(5),
                        LottoNumber(6)
                    )
                )
            )
            assertThat(actual.value[1]).isEqualTo(
                Lotto.from(
                    listOf(
                        LottoNumber(11),
                        LottoNumber(12),
                        LottoNumber(13),
                        LottoNumber(14),
                        LottoNumber(15),
                        LottoNumber(16)
                    )
                )
            )
        })
    }

    companion object {
        private lateinit var lottoGenerator: SequentialLottoNumberGenerator

        @BeforeAll
        @JvmStatic
        fun init() {
            val lottos = listOf(
                Lotto.from(
                    listOf(
                        LottoNumber(1),
                        LottoNumber(2),
                        LottoNumber(3),
                        LottoNumber(4),
                        LottoNumber(5),
                        LottoNumber(6)
                    )
                ),
                Lotto.from(
                    listOf(
                        LottoNumber(11),
                        LottoNumber(12),
                        LottoNumber(13),
                        LottoNumber(14),
                        LottoNumber(15),
                        LottoNumber(16)
                    )
                )
            )
            lottoGenerator = SequentialLottoNumberGenerator(lottos)
        }
    }
}
