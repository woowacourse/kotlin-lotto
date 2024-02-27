package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoStoreTest {
    private val automaticLottoStore =
        LottoStore.buyLottos(
            listOf(),
            PurchaseOrder(5000),
            object : LottoNumberGenerator {
                override fun generate() = listOf(1, 2, 3, 4, 5, 6)
            },
        )

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6])
    fun `수동으로 로또를 발행한다`(number: Int) {
        val manualLottoNumbers = listOf(1, 2, 3, 4, 5, 6)
        val manualLottos = listOf(Lotto.create(manualLottoNumbers), Lotto.create(manualLottoNumbers))
        val manualLottoStore =
            LottoStore.buyLottos(
                manualLottos,
                PurchaseOrder(2000),
                object : LottoNumberGenerator {
                    override fun generate() = listOf(1, 2, 3, 4, 5, 6)
                },
            )

        manualLottoStore.lottos.forEach { lotto ->
            val actual = lotto.contains(LottoNumber.from(number))
            assertThat(actual).isEqualTo(true)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6])
    fun `로또의 값을 임의로 지정해주면 지정한 로또 번호로 로또가 발행된다`(number: Int) {
        automaticLottoStore.lottos.forEach { lotto ->
            val actual = lotto.contains(LottoNumber.from(number))
            assertThat(actual).isEqualTo(true)
        }
    }

    @Test
    fun `로또 한장 가격이 1,000원 일 때, 5,000원을 투입하면 5개의 로또가 발행된다`() {
        val actual = automaticLottoStore.lottos.size
        assertThat(actual).isEqualTo(5)
    }
}
