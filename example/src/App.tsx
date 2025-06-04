import { Text, View, StyleSheet } from 'react-native';
import { getThreads } from '@vali98/react-native-cpu-info';


export default function App() {
  return (
    <View style={styles.container}>
      <Text>Thread Count: {getThreads()}</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
});
