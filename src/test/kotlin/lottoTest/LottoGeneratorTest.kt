package lottoTest

import lotto.service.LottoGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class LottoGeneratorTest {
    private val lottoGenerator = LottoGenerator()

    @Test
    @DisplayName("입력받은 횟수만큼의 로또를 만들 수 있다")
    fun t1() {
        val manyLotto = lottoGenerator.makeLotto(10)
        assertThat(manyLotto).hasSize(10)
    }
}
