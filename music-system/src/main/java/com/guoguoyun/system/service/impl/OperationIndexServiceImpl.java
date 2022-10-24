package com.guoguoyun.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guoguoyun.common.constant.MusicCode;
import com.guoguoyun.common.utils.DateUtils;
import com.guoguoyun.common.utils.SecurityUtils;
import com.guoguoyun.system.domain.Album;
import com.guoguoyun.system.domain.AlbumSong;
import com.guoguoyun.system.domain.AlbumSongRelation;
import com.guoguoyun.system.params.OperationIndexParams;
import com.guoguoyun.system.service.IAlbumService;
import com.guoguoyun.system.service.IAlbumSongRelationService;
import com.guoguoyun.system.service.IAlbumSongService;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Service;
import com.guoguoyun.system.mapper.OperationIndexMapper;
import com.guoguoyun.system.domain.OperationIndex;
import com.guoguoyun.system.service.IOperationIndexService;

/**
 * 运营管理-首页管理Service业务层处理
 *
 * @author ruoyi
 * @date 2022-02-23
 */
@Service
public class OperationIndexServiceImpl extends ServiceImpl<OperationIndexMapper, OperationIndex> implements IOperationIndexService
{
    @Autowired
    private OperationIndexMapper operationIndexMapper;

    @Autowired
    private IAlbumService albumService;

    @Autowired
    private IAlbumSongService albumSongService;

    @Autowired
    private IAlbumSongRelationService albumSongRelationService;

    /**
     * 查询运营管理-首页管理
     *
     * @param id 运营管理-首页管理主键
     * @return 运营管理-首页管理
     */
    @Override
    public OperationIndex selectOperationIndexById(Long id)
    {
        return operationIndexMapper.selectOperationIndexById(id);
    }

    /**
     * 查询运营管理-首页管理列表
     *
     * @param operationIndex 运营管理-首页管理
     * @return 运营管理-首页管理
     */
    @Override
    public List<OperationIndex> selectOperationIndexList(OperationIndex operationIndex)
    {
        return operationIndexMapper.selectOperationIndexList(operationIndex);
    }

    @Override
    public Map<String, Object> selectOperationIndexList() {
        List<OperationIndex> list = this.list();
        Map<String, Object> map = new HashMap<>();
        if (list.size() != 0){
            Map<Integer, List<OperationIndex>> integerListMap = list.stream().collect(Collectors.groupingBy(t -> t.getType()));
            integerListMap.keySet().forEach(t -> {
                List<Long> collect = integerListMap.get(t).stream().map(f -> f.getAlbumSongId()).collect(Collectors.toList());
                if (collect.size() != 0){
                    if (t == MusicCode.INDEX_CHOICENESS_ALBUM){
                        QueryWrapper<Album> wrapper = new QueryWrapper<>();
                        wrapper.lambda().in(Album::getId,collect);
                        List<Album> albums = albumService.list(wrapper);
                        map.put("choicenessAlbums",albums);
                    }else {
                        QueryWrapper<AlbumSong> wrapper = new QueryWrapper<>();
                        wrapper.lambda().in(AlbumSong::getId,collect);
                        List<AlbumSong> albumSongs = albumSongService.list(wrapper);
                        albumSongs.forEach(f -> {
                            QueryWrapper<AlbumSongRelation> albumSongRelationQueryWrapper = new QueryWrapper<>();
                            albumSongRelationQueryWrapper.lambda().eq(AlbumSongRelation::getSongId,f.getId());
                            List<AlbumSongRelation> relationList = albumSongRelationService.list(albumSongRelationQueryWrapper);
                            if (relationList.size() != 0){
                                QueryWrapper<Album> albumQueryWrapper = new QueryWrapper<>();
                                albumQueryWrapper.lambda().eq(Album::getId,relationList.get(0).getAlbumId());
                                albumQueryWrapper.lambda().eq(Album::getDeleteType,0);
                                List<Album> albumList = albumService.list(albumQueryWrapper);
                                if (albumList.size() != 0){
                                    f.setAlbum(albumList.get(0));
                                }
                            }

                        });
                        map.put("hotSong",albumSongs);
                    }
                }


            });
        }
        return map;
    }

    /**
     * 新增运营管理-首页管理
     *
     * @param operationIndex 运营管理-首页管理
     * @return 结果
     */
    @Override
    public int insertOperationIndex(OperationIndex operationIndex)
    {
        operationIndex.setCreateTime(DateUtils.getNowDate());
        operationIndex.setUpdateTime(DateUtils.getNowDate());
        operationIndex.setId(IdUtil.getSnowflakeNextId());
        operationIndex.setCreateBy(SecurityUtils.getLoginUser().getUsername());
        operationIndex.setUpdateBy(SecurityUtils.getLoginUser().getUsername());
        return operationIndexMapper.insertOperationIndex(operationIndex);
    }

    @Override
    public void insertOperationIndex(OperationIndexParams operationIndexParams) {
        List<OperationIndex> list = this.list();
        if (list.size() != 0){
            this.removeByIds(list.stream().map(t -> t.getId()).collect(Collectors.toList()));
        }
        if (ObjectUtil.isNotEmpty(operationIndexParams.getChoicenessAlbums())){
            int i = 0;
//            operationIndexParams.getChoicenessAlbums().forEach(t -> {
//                this.insertOperationIndex(t);
//            });

            for (OperationIndex t:operationIndexParams.getChoicenessAlbums()) {
                t.setRemark(i+"");
                this.insertOperationIndex(t);
                i++;
            }
        }
        if (ObjectUtil.isNotEmpty(operationIndexParams.getHotSongs())){
            int i = 0;
            for (OperationIndex t:operationIndexParams.getHotSongs()) {
                t.setRemark(i+"");
                this.insertOperationIndex(t);
                i++;
            }
        }
    }

    /**
     * 修改运营管理-首页管理
     *
     * @param operationIndex 运营管理-首页管理
     * @return 结果
     */
    @Override
    public int updateOperationIndex(OperationIndex operationIndex)
    {
        operationIndex.setUpdateTime(DateUtils.getNowDate());
        return operationIndexMapper.updateOperationIndex(operationIndex);
    }

    /**
     * 批量删除运营管理-首页管理
     *
     * @param ids 需要删除的运营管理-首页管理主键
     * @return 结果
     */
    @Override
    public int deleteOperationIndexByIds(Long[] ids)
    {
        return operationIndexMapper.deleteOperationIndexByIds(ids);
    }

    /**
     * 删除运营管理-首页管理信息
     *
     * @param id 运营管理-首页管理主键
     * @return 结果
     */
    @Override
    public int deleteOperationIndexById(Long id)
    {
        return operationIndexMapper.deleteOperationIndexById(id);
    }
}
