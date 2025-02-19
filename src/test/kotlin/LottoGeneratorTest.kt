import domain.model.PurchasePrice
import domain.service.LottoGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoGeneratorTest {
    @Test
    fun `구입 금액이 2천원이면 로또를 2개 발행한다`() {
        val money = PurchasePrice(2000)
        assertThat(LottoGenerator(money).makeLotto().values.size).isEqualTo(2)
    }
}
