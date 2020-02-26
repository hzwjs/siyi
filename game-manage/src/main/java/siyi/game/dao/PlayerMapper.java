package siyi.game.dao;

import org.springframework.stereotype.Repository;
import siyi.game.dao.entity.Player;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface PlayerMapper extends Mapper<Player> {
}
