package lottoTest

import lotto.domain.Lotto
import lotto.domain.service.LottoGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class LottoGeneratorTest {
    private val lottoGenerator = LottoGenerator()

    @Test
    @DisplayName("하나의 로또를 만들 수 있다")
    fun t1() {
        val lotto = lottoGenerator.genLotto()
        assertThat(lotto).isInstanceOfSatisfying(Lotto::class.java) {
            assertThat(lotto.value).hasSize(6)
        }
    }

    @Test
    @DisplayName("입력받은 횟수만큼의 로또를 만들 수 있다")
    fun t2() {
        val manyLotto = lottoGenerator.genManyLotto(10)
        assertThat(manyLotto).hasSize(10)
        manyLotto.forEach { lotto ->
            assertThat(lotto).isInstanceOf(Lotto::class.java)
        }
    }
}
