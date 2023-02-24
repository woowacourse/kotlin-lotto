package lotto.model.generator

import lotto.model.Lotto
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class UserLottoGeneratorTest {

    @Test
    fun `정해진 자동 로또 장 수 만큼 로또를 발급하여 수동 로또와 합쳐 사용자 로또를 반환한다`() {
        val manualLotto = listOf(Lotto(1, 2, 3, 4, 5, 6))
        Assertions.assertThat(UserLottoGenerator().generateLotto(manualLotto, 4).lotto.size).isEqualTo(5)
    }
}
