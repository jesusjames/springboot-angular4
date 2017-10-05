package com.uscosoft.angular4springboot.service.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.uscosoft.angular4springboot.model.Album;

public class AlbumMapper implements RowMapper<Album>{

	@Override
	public Album mapRow(ResultSet rs, int rowNum) throws SQLException {
		Album album = new Album();
		album.setAlbumId(rs.getInt("album_id"));
		album.setId(rs.getInt("id"));
		album.setTitle(rs.getString("title"));
		album.setUrl(rs.getString("url"));
		album.setThumbnailUrl(rs.getString("thumbnailurl"));
		return album;
	}

}
