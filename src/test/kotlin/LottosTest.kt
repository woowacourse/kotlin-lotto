import domain.Lottos
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottosTest {

    @Test
    fun `Lottos가 잘 생성되었는지 확인`() {
        val list = Lottos(
            intArrayOf(1, 2, 3, 4, 5, 6),
            intArrayOf(3, 4, 5, 6, 7, 8),
        )
        assertThat(list.lottos.size).isEqualTo(2)
    }

    @Test
    fun `Lottos 2개를 합쳤을 때 길이 확인`() {
        val lottos1 = Lottos(
            intArrayOf(1, 2, 3, 4, 5, 6),
            intArrayOf(3, 4, 5, 6, 7, 8),
        )
        val lottos2 = Lottos(
            intArrayOf(1, 4, 6, 8, 10, 11),
            intArrayOf(3, 6, 8, 12, 14, 20),
        )
        assertThat((lottos1 + lottos2).lottos.size).isEqualTo(4)
    }
}
