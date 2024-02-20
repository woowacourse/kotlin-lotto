import model.Buyer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class BuyerTest {
    @ParameterizedTest
    @CsvSource("8000,8", "1000,1", "15000,15")
    fun `발행한 로또 수량 계산`(purchaseAmount: Int, numberOfLotto: Int) {
        val buyer = Buyer(purchaseAmount)
        val actual = buyer.calculateNumberOfLotto()

        assertThat(actual).isEqualTo(numberOfLotto)
    }
}