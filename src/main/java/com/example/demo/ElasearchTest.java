package com.example.demo;


import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.data.geo.Point;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/ela")
public class ElasearchTest {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate ;

    @RequestMapping(value = "/index")
    public String test(){
        Shop shop = new Shop();
        shop.setId("1812180000013");
        shop.setShopName("测试1111");
        shop.setShopDesc("这是描述字段333333");
        shop.setShopDiscount(0.8);
        shop.setShopImgPath("/img/xiaomi.jpg");
        GeoPoint geoPoint = GeoPoint.fromPoint( new Point( new Double( "37.12121" )  , new Double( "113.2324566" ) ) );
        shop.setLocation( geoPoint );
        IndexQuery indexQuery  = new IndexQuery();
        indexQuery.setId(shop.getId());
        indexQuery.setObject(shop);
        indexQuery.setIndexName("shop");
        indexQuery.setType("shop");
        String index = elasticsearchTemplate.index(indexQuery);

        return "success"+index;
    }

    @RequestMapping(value = "/update")
    public String update(){
        Map<String , Object> sourceMap = new HashMap<>();
        sourceMap.put( "shopName","修改后的名字22" );
        sourceMap.put( "shopDesc","描述2222" );
        UpdateRequest request = new UpdateRequest();
        request.doc(sourceMap);
        UpdateQuery build = new UpdateQueryBuilder().withClass( Shop.class )
                .withUpdateRequest(request)
                .build();
        elasticsearchTemplate.update( build );
        return "success";
    }

    @RequestMapping(value = "/updateBySearch")
    public String updateBySearch(){
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        queryBuilder.must( QueryBuilders.matchQuery("shopName", "测试1111"));

        NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder();
        NativeSearchQuery searchQuery = searchQueryBuilder.withIndices( "shop" ).build();
        List<Shop> shops = elasticsearchTemplate.queryForList( searchQuery, Shop.class );
        if ( !CollectionUtils.isEmpty( shops ) ){
            Shop shop = shops.get( 0 );
            shop.setShopDesc( "这是先查询后修改的结果" );

            Map<String , Object> sourceMap = new HashMap<>();
            sourceMap.put( "shopName","修改后的名字22" );
            sourceMap.put( "shopDesc","描述23334" );
            UpdateRequest request = new UpdateRequest();
            request.doc(sourceMap);
            UpdateQuery build = new UpdateQueryBuilder().withClass( Shop.class )
                    .withId( shop.getId() )
                    .withUpdateRequest(request)
                    .build();
            elasticsearchTemplate.update( build );
            /*IndexQuery indexQuery  = new IndexQuery();
            indexQuery.setId(shop.getId());
            indexQuery.setObject(shop);
            indexQuery.setIndexName("shop");
            indexQuery.setType("shop");
            elasticsearchTemplate.index(indexQuery);*/
        }
        return "success";
    }
}
