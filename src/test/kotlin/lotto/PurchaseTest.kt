package lotto

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PurchaseTest {
    @Test
    fun `로또는 1000원 단위로 구입할 수 있다`() {
        val amountOfPurchase = "10001"
        assertThrows<IllegalArgumentException> { Purchase(amountOfPurchase) }
    }

    @Test
    fun `구입 금액에 맞는 로또 개수를 계산할 수 있다`() {
        val amountOfPurchase = "10000"
        assertThat(Purchase(amountOfPurchase).calculateAmountOfLottos(amountOfPurchase)).isEqualTo(10)
    }

    @ParameterizedTest
    @ValueSource(strings = ["1000원", "삼천원", "billion"])
    fun `구입 금액은 숫자로 입력되어야 한다`(input: String) {
        runCatching {
            Purchase(input)
        }.onFailure { exception ->
            assertThat(exception)
                .isInstanceOf(java.lang.IllegalArgumentException::class.java)
                .hasMessage("구입 금액은 숫자로 입력해주세요")
        }
    }
}
