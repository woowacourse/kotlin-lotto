package domain.service

import domain.fixture.createLotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AutoLottoTest {
    @Test
    fun `구매정보를 전달하면 수동 로또와 자동 로또가 합쳐진 로또를 반환한다`() {
        val lottos =
            FakeAutoLottoMachine(
                createLotto(1, 2, 3, 4, 5, 6),
            ).generate()

        assertThat(lottos).isEqualTo(
            createLotto(1, 2, 3, 4, 5, 6),
        )
    }
}
