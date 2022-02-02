import React, { useEffect, useState } from 'react';
import { View, Text, StyleSheet, Image } from 'react-native';
import CardImages from './CardImages';

const HoleCard = function(props) {

    const [imagePath, setImagePath] = useState("2_of_clubs");

    useEffect(() => {
        setImagePath(props.path);
        console.log(props.path)
    }, [props])

    // function getImage() {
    //     console.log(props.HoleCard);
    //     let data = props.HoleCard.split(" ");
    //     let suit = data[0];
    //     let value = data[1];
    //     return "2_of_clubs.png";
    // }

    return(
        <View style={styles.imageView}>
            <Image
                style ={styles.image}
                // source={require("../assets/cards/SPADESTWO.png")}
                source={CardImages["cards"][props.path]}
            />
        </View>
    );
}

const styles = StyleSheet.create({
    imageView: {
    // display: "flex",
    // flexDirection: "row",
    width: "40%",
    // height: "100%",
    marginLeft: "auto",
    marginRight: "auto",
    // alignItems: "center",
    // textAlign: "center",
    // justifyContent: "space-evenly",
    },
    image: {
        resizeMode: "contain",
        width: null,
        height: 200,
        // marginBottom: "5%",
        // marginTop: "5%",
    },
});

export default HoleCard;
