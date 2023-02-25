package lotto.model.generator

import lotto.model.Lotto
import lotto.model.ManualLottos
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class UserLottoGeneratorTest {

    @Test
    fun `정해진 자동 로또 장 수 만큼 로또를 발급하여 수동 로또와 합쳐 사용자 로또를 반환한다`() {
        val manualLotto = ManualLottos(1, listOf(Lotto(1, 2, 3, 4, 5, 6)))
        val userLotto = UserLottoGenerator().generateLotto(manualLotto, 5)
        assertThat(userLotto.lotto.size).isEqualTo(5)
    }
}
