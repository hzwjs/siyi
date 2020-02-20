package siyi.game.dao;

import org.springframework.stereotype.Repository;
import siyi.game.dao.entity.Menu;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface MenuMapper extends Mapper<Menu> {
}
