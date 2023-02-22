import domain.Count
import domain.Money
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test

class CountTest {

    @Test
    fun `입력된 금액이 1000원이고 수동으로 사려는 로또의 개수가 2면 예외가 발생한다`() {
        assertThatIllegalArgumentException()
            .isThrownBy { Count(Money(1000), 2) }
            .withMessageContaining("원하는 수동 로또 수량을 입력된 금액으로 구매할 수 없습니다.")
    }

    @Test
    fun `입력된 금액이 10000원이고 수동으로 사려는 로또의 개수가 2면 자동으로 살 수 있는 로또의 개수는 8이다`() {
        val count = Count(Money(10000), 2)
        val autoLottoCount = count.getAutoLottoCount()
        assertThat(autoLottoCount).isEqualTo(8)
    }
}
