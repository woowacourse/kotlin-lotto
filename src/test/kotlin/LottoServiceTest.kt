import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class LottoServiceTest {
    @Test
    @DisplayName("복권은 6개의 1부터 45까지의 랜덤 숫자로 이루어진 리스트이다")
    fun t1() {
        val lottoService:LottoService = LottoService()
        lottoService.getLotto()
    }
}