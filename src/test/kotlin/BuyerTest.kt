import model.Buyer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class BuyerTest {
    @ParameterizedTest
    @CsvSource("8000,8", "1000,1", "15000,15")
    fun `구매 금액 1000원당, 로또를 1장씩 구매할 수 있다`(
        purchaseAmount: Int,
        numberOfLotto: Int,
    ) {
        val buyer = Buyer(purchaseAmount)
        val actual = buyer.calculateNumberOfLotto()

        assertThat(actual).isEqualTo(numberOfLotto)
    }
}
